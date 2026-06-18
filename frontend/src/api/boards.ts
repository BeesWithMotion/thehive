import { apiGet } from "./client";
import type { Board } from "../types/board";

export function getBoards() {
    return apiGet<Board[]>('/api/boards');
}

export function getBoardByAbbreviation(boardAbbreviation: string) {
    return apiGet<Board>(`/api/boards/${boardAbbreviation}`);
}