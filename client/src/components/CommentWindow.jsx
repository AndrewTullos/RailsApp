import React, { useState, useEffect } from "react";

import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";

import {
	User,
	Settings,
	Grid,
	Bookmark,
	Heart,
	MessageCircle,
} from "lucide-react";

const url = `http://localhost:8080/api/comment`;

function CommentWindow({ clipId }) {
	const [comments, setComments] = useState([]);
	const [errors, setErrors] = useState([]);

	useEffect(() => {
		const fetchCommentData = async () => {
			try {
				const response = await fetch(`${url}/${clipId}`);
				if (response.ok) {
					const data = await response.json();
					setComments(data);
				}
			} catch (err) {
				setErrors([err.message || "An error occurred."]);
				console.error("Fetch Error:", err);
			}
		};

		fetchCommentData();
	}, [setComments]);

	return (
		<div className=" mb-3 border-input placeholder:text-muted-foreground focus-visible:border-ring focus-visible:ring-ring/50 aria-invalid:ring-destructive/20 dark:aria-invalid:ring-destructive/40 aria-invalid:border-destructive dark:bg-input/30 flex field-sizing-content min-h-16 w-full rounded-md border bg-transparent px-3 py-2 text-base shadow-xs transition-[color,box-shadow] outline-none focus-visible:ring-[3px] disabled:cursor-not-allowed disabled:opacity-50 md:text-sm">
			{comments.map((comment) => (
				<div key={comment.id} className="m-2 border-2">
					<p className="text-pretty text-1xl font-semibold tracking-tight text-gray-100 sm:text-1xl">
						Posted: {new Date(comment.created_at).toUTCString()}
					</p>
					<Avatar className="h-8 w-8 sm:h-8 sm:w-8">
						<AvatarImage
							src={
								comment.commenter.profilePicture
									? comment.commenter.profilePicture
									: null
							}
							alt={`@${comment.commenter.username}`}
						/>
						<AvatarFallback>
							<User />
						</AvatarFallback>
					</Avatar>

					<h2 className="text-2xl font-bold">{comment.commenter.username}</h2>
					<p className="text-pretty text-1xl font-semibold tracking-tight text-gray-100 sm:text-1xl">
						{comment.text}
					</p>
				</div>
			))}
		</div>
	);
}

export default CommentWindow;
