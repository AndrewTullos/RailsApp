import React, { useState, useEffect } from "react";

import { toast } from "sonner";
import { Heart, MessageCircle } from "lucide-react";

function HeartButton({ loggedInUser, clipId, userProfile }) {
	const [isClicked, setIsClicked] = useState(false);
	const [like, setLike] = useState("");
	const [errors, setErrors] = useState([]);

	const handleClick = () => {
		setIsClicked(!isClicked);

		const likeClip = async () => {
			try {
				const likePayload = {
					liker: loggedInUser,
					clip: {
						clipId: clipId,
						userProfile: userProfile,
					},
				};

				const response = await fetch("http://localhost:8080/api/like", {
					method: "POST",
					headers: {
						"Content-Type": "application/json",
					},
					body: JSON.stringify(likePayload),
				});

				if (response.ok) {
					alert("Clip liked");
				} else {
					const errorData = await response.json();
					setErrors([errorData.message || "Failed to post comment."]);
				}
			} catch (err) {
				setErrors([err.message || "An error occurred."]);
				console.error("Fetch Error:", err);
			}
		};

		likeClip();
	};

	return (
		<button onClick={handleClick}>
			{isClicked ? (
				<Heart
					className="h-4 w-4"
					size={64}
					color="red"
					strokeWidth={2}
					fill="red"
				/>
			) : (
				<Heart
					className="h-4 w-4"
					size={64}
					color="currentColor"
					strokeWidth={2}
				/>
			)}
		</button>
	);
}

export default HeartButton;
