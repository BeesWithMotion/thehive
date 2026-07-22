export type Post = {
    postId: number,
    threadId: number,
    postContent: string,
    postAuthor: string,
    postDate: string,
    image: ImageResponse | null
}

export type ImageResponse = {
    imageId: number,
    postId: number,
    originalFileName: string,
    contentType: string,
    uploadDateTime: string,
    imageUrl: string
}

export type CreatePostRequest = {
    postContent: string
}