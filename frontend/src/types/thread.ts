export type Thread = {
    threadId: number,
    boardAbbreviation: string,
    threadTitle: string,
    threadDescription: string,
    threadAuthor: string,
    threadDate: string
};

export type CreateThreadRequest = {
    threadTitle: string,
    threadDescription: string,
    threadAuthor: string
};