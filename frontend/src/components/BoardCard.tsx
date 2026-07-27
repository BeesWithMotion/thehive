import type { Board } from "../types/board";
import { Link } from "react-router-dom";

type BoardCardProps = {
    board: Board;
}

export function BoardCard({ board }: BoardCardProps) {
    return (
        <article key={board.boardId}>
            <div style={{display: "flex", alignItems: "baseLine", justifyContent: "center", gap: "0.5rem"}}>
                <h2>
                    <Link to={`/boards/${board.boardAbbreviation}`}>/{board.boardAbbreviation}/</Link>
                </h2>
                <h3>{board.boardName}</h3>
            </div>
            <p>{board.boardDescription}</p>
        </article>
    )
}
