import { apiGet } from "./client";
import type { Post } from "../types/post";

export function getPosts(boardAbbreviation: string, threadId: number) {
    return apiGet<Post[]>(`/api/boards/${boardAbbreviation}/threads/${threadId}/posts`);
}