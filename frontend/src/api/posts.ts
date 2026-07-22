import { apiGet, apiPost, apiPostFormData } from "./client";
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

export function createPostWithImage(
    boardAbbreviation: string,
    threadId: number,
    request: CreatePostRequest,
    image: File | null
) {
    const formData = new FormData();

    formData.append(
        "post",
        new Blob([JSON.stringify(request)], {
            type: "application/json",
        })
    );

    if (image) {
        formData.append("image", image);
    }

    return apiPostFormData<Post>(
        `/api/boards/${boardAbbreviation}/threads/${threadId}/posts`,
        formData
    );
}
