import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getBoardByAbbreviation } from "../api/boards";
import { getThreads } from "../api/threads";
import type { Board } from "../types/board";
import type { Thread } from "../types/thread";

export function BoardPage() {
    const { boardAbbreviation } = useParams<{ boardAbbreviation: string }>();

    const [board, setBoard] = useState<Board | null>(null);
    const [threads, setThreads] = useState<Thread[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    useEffect(() => {
        if(!boardAbbreviation) {
            setErrorMessage("No board abbreviation provided");
            setIsLoading(false);
            return;
        }

        Promise.all([
            getBoardByAbbreviation(boardAbbreviation),
            getThreads(boardAbbreviation)
        ])
            .then(([boardResponse, threadResponse]) => {
                setBoard(boardResponse);
                setThreads(threadResponse);
            })
            .catch(error => setErrorMessage(error.message))
            .finally(() => setIsLoading(false))
    }, [boardAbbreviation, threads]);

    if(isLoading) {
        return <main>Loading board...</main>;
    }

    if(errorMessage) {
        return (
            <main>
                <p>{errorMessage}</p>
                <Link to="/">Back to boards</Link>
            </main>
        )
    }

    if(!board) {
        return (
            <main>
                <p>Board not found</p>
                <Link to="/">Back to boards</Link>
            </main>
        )
    }

    return (
        <main>
            <nav>
                <Link to="/">Boards</Link>
            </nav>

            <header>
                <h1>/{board.boardAbbreviation}/ - {board.boardName}</h1>
                <p>{board.boardDescription}</p>
            </header>

            <section>
                <Link to={`/boards/${board.boardAbbreviation}/new`}>
                    Create a thread
                </Link>

                <h2>Active Threads</h2>

                {threads.length === 0 ? (
                    <p>No threads yet.</p>
                ) : (
                    threads.map((thread) => (
                        <article key={thread.threadId}>
                            <header>
                                <strong>
                                    <Link to={`/boards/${board.boardAbbreviation}/threads/${thread.threadId}`}>
                                        {thread.threadTitle}
                                    </Link>
                                </strong>
                                {" . "}
                                <time dateTime={thread.threadDate}>
                                    {new Date(thread.threadDate).toLocaleString()}
                                </time>
                                {" . "}
                            </header>
                        </article>
                    ))
                )}

            </section>
        </main>
    )
}