import { useEffect, useState } from "react";
import { getBoards } from "../api/boards";
import { BoardCard } from "../components/BoardCard";
import type { Board } from "../types/board";
import {SideCard} from "../components/SideCard.tsx";

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
            <SideCard/>

            <h1>The Hive</h1>

            <section>
                {boards.map((board) => (
                    <BoardCard
                        key={board.boardId}
                        board={board}
                    />
                ))}
            </section>
        </main>
    );
}