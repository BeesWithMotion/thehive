import type { Thread } from "../types/thread";
import {Link} from "react-router-dom";

type ThreadCardProps = {
    thread: Thread;
}

export function ThreadCard({ thread }: ThreadCardProps) {
    return (
        <article key={thread.threadId}>
            <header style={{display: "flex", alignItems: "baseLine", justifyContent: "center", gap: "0.5rem"}}>
                <strong>
                    <Link to={`/boards/${thread.boardAbbreviation}/threads/${thread.threadId}`}>
                        {thread.threadTitle}
                    </Link>
                </strong>
                {" - "}
                <time dateTime={thread.threadDate}>
                    {new Date(thread.threadDate).toLocaleString()}
                </time>
                {" - "}
                <p>
                    {thread.threadAuthor}
                </p>
                {" - "}
                <p>
                    #{thread.threadId}
                </p>
            </header>
        </article>
    )
}