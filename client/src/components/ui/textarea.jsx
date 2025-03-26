import React, { useState, useEffect } from "react";

import { toast } from "sonner";

import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";

function Textarea({ userProfile, clipId, loggedInUser, className, ...props }) {
	const [comment, setComment] = useState("");
	const [errors, setErrors] = useState([]);

	function handleSubmit(event) {
		event.preventDefault();

		const postComment = async () => {
			try {
				const commentPayload = {
					commenter: loggedInUser,
					clip: {
						clipId: clipId,
						userProfile: userProfile,
					},
					text: comment,
				};

				const response = await fetch("http://localhost:8080/api/comment", {
					method: "POST",
					headers: {
						"Content-Type": "application/json",
					},
					body: JSON.stringify(commentPayload),
				});

				if (response.ok) {
					// const result = await response.json();
					alert("Coment posted!");
					setComment("");
				} else {
					const errorData = await response.json();
					setErrors([errorData.message || "Failed to post comment."]);
				}
			} catch (err) {
				setErrors([err.message || "An error occurred."]);
				console.error("Fetch Error:", err);
			}
		};

		postComment();

		toast({
			title: "Comment posted:",
			description: (
				<pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
					<code className="text-white">
						{JSON.stringify({ comment }, null, 2)}
					</code>
				</pre>
			),
		});
	}

	const handleChange = (event) => {
		setComment(event.target.value);
	};

	return (
		<div>
			<textarea
				data-slot="textarea"
				value={comment}
				name="comment"
				onChange={handleChange}
				placeholder="Enter comment..."
				className={cn(
					"border-input placeholder:text-muted-foreground focus-visible:border-ring focus-visible:ring-ring/50 aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive dark:bg-input/30 flex field-sizing-content min-h-16 w-full rounded-md border bg-transparent px-3 py-2 text-base shadow-xs transition-[color,box-shadow] outline-none focus-visible:ring-[3px] disabled:cursor-not-allowed disabled:opacity-50 md:text-sm",
					className
				)}
				{...props}
			/>
			<Button onClick={handleSubmit}>Submit</Button>
			{errors.length > 0 && (
				<div className="text-red-500 mt-2">{errors[0]}</div>
			)}
		</div>
	);
}

export { Textarea };
