import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getBoardByAbbreviation } from "../api/boards";
import type { Board } from "../types/board";

export function BoardPage() {
    const { boardAbbreviation } = useParams<{ boardAbbreviation: string }>();

    const [board, setBoard] = useState<Board | null>(null);
    const [isLoading, setIsLoading] = useState(true);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    useEffect(() => {
        if(!boardAbbreviation) {
            setErrorMessage("No board abbreviation provided");
            setIsLoading(false);
            return;
        }

        getBoardByAbbreviation(boardAbbreviation)
            .then(setBoard)
            .catch(error => setErrorMessage(error.message))
            .finally(() => setIsLoading(false));
    }, [boardAbbreviation]);

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

            </section>
        </main>
    )
}