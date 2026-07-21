import { useState } from "react";
import type { FormEvent } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { createThread } from "../api/threads";

export function CreateThreadPage() {
    const { boardAbbreviation } = useParams<{ boardAbbreviation: string }>();
    const navigate = useNavigate();

    const [threadTitle, setThreadTitle] = useState("");
    const [threadDescription, setThreadDescription] = useState("");

    const [isSubmitting, setIsSubmitting] = useState(false);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);

    async function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        if (!boardAbbreviation) {
            setErrorMessage("No board abbreviation provided");
            return;
        }

        setIsSubmitting(true);
        setErrorMessage(null);

        try {
            const createdThread = await createThread(boardAbbreviation, {
                threadTitle,
                threadDescription,
            });

            navigate(`/boards/${createdThread.boardAbbreviation}/threads/${createdThread.threadId}`);
        } catch (error) {
            const message = error instanceof Error ? error.message : "Error while creating thread";
            setErrorMessage(message);
        } finally {
            setIsSubmitting(false);
        }
    }

    return (
        <main>
            <nav>
                <Link to={boardAbbreviation ? `/boards/${boardAbbreviation}` : "/"}>
                    Back to board
                </Link>
            </nav>

            <h1>Create a thread</h1>

            {errorMessage && <p>{errorMessage}</p>}

            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="threadTitle">Title</label>
                    <input
                        id="threadTitle"
                        name="threadTitle"
                        type="text"
                        value={threadTitle}
                        onChange={(event) => setThreadTitle(event.target.value)}
                        required
                        maxLength={255}
                    />
                </div>

                <div>
                    <label htmlFor="threadDescription">Description</label>
                    <textarea
                        id="threadDescription"
                        name="threadDescription"
                        value={threadDescription}
                        onChange={(event) => setThreadDescription(event.target.value)}
                    />
                </div>

                <button type="submit" disabled={isSubmitting}>
                    {isSubmitting ? "Creating..." : "Create thread"}
                </button>
            </form>
        </main>
    )
}