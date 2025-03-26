import React, { useEffect } from "react";
import { Link } from "react-router-dom";
import { User } from "lucide-react";
import { Avatar, AvatarImage, AvatarFallback } from "@/components/ui/avatar";
import HeartComponent from "./HeartComponent";
import { Textarea } from "@/components/ui/textarea";
import TextareaForm from "./TextAreaForm";
import CommentWindow from "./CommentWindow";

function ClipCard({
	loggedInUser,
	userData,
	userProfile,
	mediaUrl,
	caption,
	clipId,
}) {
	return (
		<div className="border-2 rounded-2xl border-secondary p-4">
			<Link to={`http://localhost:5173/profile/${userProfile.userId}`}>
				<h1 className="text-2xl font-bold">{userProfile.username}</h1>
				<Avatar className="">
					<AvatarImage
						src={userProfile.profilePicture ? userProfile.profilePicture : null}
						alt={`${userProfile.username}`}
					/>
					<AvatarFallback>
						<User />
					</AvatarFallback>
				</Avatar>
			</Link>
			<iframe
				width="100%"
				height="640"
				src={mediaUrl}
				title={caption}
				allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
				allowFullScreen
				className="max-w-full"
			></iframe>
			<p>{caption}</p>
			<div className="transition-transform hover:scale-105">
				<HeartComponent
					userProfile={userProfile}
					clipId={clipId}
					loggedInUser={loggedInUser}
				/>
				<CommentWindow clipId={clipId} />
				<Textarea
					userProfile={userProfile}
					clipId={clipId}
					loggedInUser={loggedInUser}
				/>
			</div>
		</div>
	);
}

export default ClipCard;
