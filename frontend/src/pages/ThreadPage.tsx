import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getPosts } from "../api/posts";
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
        </main>
    )
}