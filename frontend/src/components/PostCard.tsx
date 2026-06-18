import type { Post } from "../types/post";

type PostCardProps = {
    post: Post;
};

export function PostCard({ post }: PostCardProps) {
    return (
        <article>
            <header>
                <strong>{post.postAuthor}</strong>
                {" · "}
                <time dateTime={post.postDate}>
                    {new Date(post.postDate).toLocaleString()}
                </time>
                {" · "}
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
    );
}