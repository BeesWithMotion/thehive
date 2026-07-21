import { useEffect, useState } from "react";
import type { FormEvent } from "react";
import { Link, useParams } from "react-router-dom";
import { getPosts, createPost } from "../api/posts";
import { getThread } from "../api/threads";
import type { Post } from "../types/post";
import type { Thread } from "../types/thread";

export function ThreadPage() {
    const { boardAbbreviation, threadId } = useParams<{
        boardAbbreviation: string;
        threadId: string;
    }>();

    const [thread, setThread] = useState<Thread | null>(null);
    const [posts, setPosts] = useState<Post[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    const [postContent, setPostContent] = useState("");
    const [isSubmitting, setIsSubmitting] = useState(false);

    async function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        if(!boardAbbreviation) {
            setErrorMessage("Invalid board abbreviation");
            return;
        }

        if(!postContent.trim()) {
            setErrorMessage("Post cannot be empty");
            return;
        }

        setIsSubmitting(true);
        setErrorMessage(null);

        try {
            const createdPost = await createPost(boardAbbreviation, Number(threadId), {
                postContent
            });

            setPosts((currentPosts) => [...currentPosts, createdPost]);
            setPostContent("");
        } catch (error) {
            const message = error instanceof Error ? error.message : "Error during post creation";
            setErrorMessage(message);
        } finally {
            setIsSubmitting(false);
        }
    }

    useEffect(() => {
        if(!boardAbbreviation || !threadId) {
            setErrorMessage("No board abbreviation or thread ID provided");
            setIsLoading(false);
            return;
        }

        Promise.all([
            getThread(boardAbbreviation, Number(threadId)),
            getPosts(boardAbbreviation, Number(threadId))
        ])
            .then(([threadResponse, postResponse]) => {
                setThread(threadResponse);
                setPosts(postResponse);
            })
            .catch(error => setErrorMessage(error.message))
            .finally(() => setIsLoading(false));
    }, [boardAbbreviation, threadId]);

    if(isLoading) {
        return <main>Loading thread...</main>;
    }

    if(errorMessage) {
        return (
            <main>
                <p>{errorMessage}</p>
                <Link to={boardAbbreviation ? `/boards/${boardAbbreviation}` : "/"}>
                    Back to board
                </Link>
            </main>
        )
    }

    if(!thread) {
        return (
            <main>
                <p>Thread not found</p>
                <Link to={boardAbbreviation ? `/boards/${boardAbbreviation}` : "/"}>
                    Back to board
                </Link>
            </main>
        )
    }

    return (
        <main>
            <nav>
                <Link to="/">Boards</Link>
                {" / "}
                <Link to={`/boards/${thread.boardAbbreviation}`}>
                    /{thread.boardAbbreviation}/
                </Link>
            </nav>

            <article>
                <header>
                    <h1>{thread.threadTitle}</h1>
                    <p>
                        Posted by {thread.threadAuthor} on {" "}
                        {new Date(thread.threadDate).toLocaleString()}
                    </p>
                </header>

                {thread.threadDescription && <p>{thread.threadDescription}</p>}
            </article>

            <section>
                <h2>Posts</h2>

                {posts.length === 0 ? (
                    <p>No replies yet.</p>
                ) : (
                    posts.map((post) => (
                        <article key={post.postId}>
                            <header>
                                <strong>{post.postAuthor}</strong>
                                {" . "}
                                <time dateTime={post.postDate}>
                                    {new Date(post.postDate).toLocaleString()}
                                </time>
                                {" . "}
                                <span>No. {post.postId}</span>
                            </header>

                            {post.image && (
                                <div>
                                    <img
                                        src={post.image.imageUrl}
                                        alt={post.image.originalFileName}
                                        style={{ maxWidth: "320px", height: "auto" }}
                                        />
                                </div>
                            )}

                            <p>{post.postContent}</p>
                        </article>
                    ))
                )}
            </section>

            <section>
                <h2>Add Buzz</h2>

                {localStorage.getItem("authToken") ? (
                    <form onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="postContent">Post content</label>
                            <textarea
                                id="postContent"
                                name="postContent"
                                value={postContent}
                                onChange={(event) => setPostContent(event.target.value)}
                                required
                            />
                        </div>

                        <button type="submit" disabled={isSubmitting}>
                            {isSubmitting ? "Buzzing..." : "Add Buzz"}
                        </button>
                    </form>
                ) : (
                    <p>
                        Please <Link to ="/login">log in</Link> to reply.
                    </p>
                )}
            </section>
        </main>
    )
}