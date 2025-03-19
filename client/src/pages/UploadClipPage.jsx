import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Video } from "lucide-react";
import { Button } from "@/components/ui/button";

function UploadClipPage() {
	const navigate = useNavigate();
	const [file, setFile] = useState();
	const [description, setDescription] = useState("");
	const [clip, setClip] = useState([]);

	async function postClip({ clip, description }) {}

	function handleSubmit(evt) {
		evt.preventDefault();

		const config = {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(agent),
		};

		fetch("http://localhost:8080/api/agent", config)
			.then((response) => {
				if (response.ok) {
					navigate("/dashboard");
				} else {
					return response.json();
				}
			})
			.then((errs) => {
				if (errs) {
					return Promise.reject(errs);
				}
			})
			.catch((errs) => {
				if (errs.length) {
					setErrors(errs);
				} else {
					setErrors([errs]);
				}
			});
	}

	function handleCancel() {
		navigate("/dashboard");
	}

	return (
		<main className="grid min-h-full place-items-center bg-primary px-6 py-24 sm:py-32 lg:px-8">
			<div className="text-center">
				<p className="text-base font-semibold text-indigo-600">
					<Video className="" />
				</p>
				<h1 className="mt-4 text-balance text-5xl font-semibold tracking-tight text-gray-200 sm:text-7xl">
					Upload
				</h1>
				<p className="mt-6 text-pretty text-lg font-medium text-gray-400 sm:text-xl/8">
					Upload Clips.
				</p>
				<form className="text-white border-1 p-2">
					<label htmlFor="clip"></label>
					<input
						type="file"
						id="clip"
						name="clip"
						accept="video/*,image/*"
						onChange={(e) => setFile(e.target.files[0])}
					/>
					<Button className="bg-indigo-600" type="submit">
						Upload
					</Button>
					<Button onClick={handleCancel}>Cancel</Button>
				</form>
			</div>
		</main>
	);
}

export default UploadClipPage;
