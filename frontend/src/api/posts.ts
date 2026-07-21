import { apiGet, apiPost } from "./client";
import type { CreatePostRequest, Post } from "../types/post";

export function getPosts(boardAbbreviation: string, threadId: number) {
    return apiGet<Post[]>(`/api/boards/${boardAbbreviation}/threads/${threadId}/posts`);
}

export function createPost(
    boardAbbreviation: string,
    threadId: number,
    request: CreatePostRequest
) {
    return apiPost<Post, CreatePostRequest>(
        `/api/boards/${boardAbbreviation}/threads/${threadId}/posts`,
        request,
    );
}