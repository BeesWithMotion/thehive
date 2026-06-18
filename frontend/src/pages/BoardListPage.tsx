import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getBoards } from "../api/boards";
import type { Board } from "../types/board";

export function BoardListPage() {
    const [boards, setBoards] = useState<Board[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    useEffect(() => {
        getBoards()
            .then(setBoards)
            .catch(error => setErrorMessage(error.message))
            .finally(() => setIsLoading(false));
    }, []);

    if(isLoading) {
        return <main>Loading boards...</main>;
    }

    if(errorMessage) {
        return <main>{errorMessage}</main>;
    }

    return (
        <main>
            <h1>The Hive</h1>

            <section>
                {boards.map((board) => (
                    <article key={board.boardId}>
                        <h2>
                            <Link to={`/boards/${board.boardAbbreviation}`}>/{board.boardAbbreviation}/</Link>
                        </h2>
                        <h3>{board.boardName}</h3>
                        <p>{board.boardDescription}</p>
                    </article>
                ))}
            </section>
        </main>
    );
}