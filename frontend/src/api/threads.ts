import { apiPost } from "./client";
import type { CreateThreadRequest, Thread } from "../types/thread";

export function createThread(
    boardAbbreviation: string,
    request: CreateThreadRequest
) {
    return apiPost<Thread, CreateThreadRequest>(
        `/api/boards/${boardAbbreviation}/threads`,
        request,
    );
}