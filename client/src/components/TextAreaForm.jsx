"use client";

import React, { useState, useEffect } from "react";
import getToken from "../functions/getToken";

import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";

// import { toast } from "@/components/hooks/use-toast";
import { Toaster } from "@/components/ui/sonner";
import { toast } from "sonner";

import { Button } from "@/components/ui/button";
import {
	Form,
	FormControl,
	FormDescription,
	FormField,
	FormItem,
	FormLabel,
	FormMessage,
} from "@/components/ui/form";
import { Textarea } from "@/components/ui/textarea";

const FormSchema = z.object({
	comment: z
		.string()
		.min(10, {
			message: "Caption must be at least 10 characters.",
		})
		.max(160, {
			message: "Caption must not be longer than 30 characters.",
		}),
});

export default function TextareaForm({
	loggedInUser,
	setLoggedInUser,
	userProfile,
	clipId,
}) {
	const [userData, setUserData] = useState([]);
	const [commenter, setCommenter] = useState([]);
	const [clip, setClip] = useState([]);
	const [text, setText] = useState([]);
	const [errors, setErrors] = useState([]);

	const userId = getToken(loggedInUser);

	useEffect(() => {
		const fetchCommenter = async () => {
			try {
				const response = await fetch(
					`http://localhost:8080/api/user/${userId}`
				);
				if (response.ok) {
					const data = await response.json();

					setCommenter(data);
				} else {
					const errorData = await response.json();
					setErrors([errorData.message || "Failed to fetch user data."]);
				}
			} catch (err) {
				setErrors([err.message || "An error occurred."]);
				console.error("Fetch Error:", err);
			}
		};

		fetchCommenter();
	}, [userId]);

	const form = useForm({
		resolver: zodResolver(FormSchema),
	});

	function onSubmit(data) {
		<Toaster />;

		const postComment = async () => {
			try {
				const response = await fetch(`http://localhost:8080/api/comment`);
				if (response.ok) {
					const data = await response.json();

					setFollowing(data);
				} else {
					const errorData = await response.json();
					setErrors([errorData.message || "Failed to fetch user data."]);
				}
			} catch (err) {
				setErrors([err.message || "An error occurred."]);
				console.error("Fetch Error:", err);
			}
		};

		postComment();

		toast({
			title: "You submitted the following values:",
			description: (
				<pre className="mt-2 w-[340px] rounded-md bg-slate-950 p-4">
					<code className="text-white">{JSON.stringify(data, null, 2)}</code>
				</pre>
			),
		});
	}

	return (
		<Form {...form}>
			<form onSubmit={form.handleSubmit(onSubmit)} className="w-2/3 space-y-6">
				<FormField
					control={form.control}
					name="comment"
					render={({ field }) => (
						<FormItem>
							{/* <FormLabel>Bio</FormLabel> */}
							<FormControl>
								<Textarea
									placeholder="Comment on clip..."
									className="resize-none"
									{...field}
								/>
							</FormControl>
							<FormDescription></FormDescription>
							<FormMessage />
						</FormItem>
					)}
				/>
				<Button type="submit">Submit</Button>
			</form>
		</Form>
	);
}
