import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import {
	Card,
	CardContent,
	CardDescription,
	CardHeader,
	CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export function UploadComponent({
	className,
	loggedInUser,
	setLoggedInUser,
	...props
}) {
	const navigate = useNavigate();

	const [file, setFile] = useState(null);
	const [errors, setErrors] = useState([]);

	const handleSubmit = (event) => {
		event.preventDefault();

		const formData = new FormData();
		if (file) {
			formData.append("file", file);
		}

		fetch("http://localhost:8080/api/v1/s3/upload", {
			method: "POST",
			body: formData,
		}).then((response) => {
			if (response.status >= 200 && response.status < 300) {
				navigate("/dashboard");
			} else {
				return response.json().then((fetchedErrors) => {
					setErrors(fetchedErrors);
				});
			}
		});
	};

	const handleChange = (event) => {
		const selectedFile = event.target.files[0];
		setFile(selectedFile);
	};

	function handleCancel() {
		navigate("/dashboard");
	}

	return (
		<div className={cn("flex flex-col gap-6", className)} {...props}>
			<Card>
				<CardHeader>
					<CardTitle className="text-2xl">Upload Clip</CardTitle>
					<CardDescription>Post a clip!</CardDescription>
				</CardHeader>
				<CardContent>
					<form onSubmit={handleSubmit}>
						<div className="flex flex-col gap-6">
							<div className="grid gap-2">
								<Label htmlFor="clip">Clip Media</Label>
								<Input
									id="file"
									name="file"
									type="file"
									accept="video/*,image/*"
									onChange={handleChange}
									required
								/>
							</div>
							{/* <div className="grid gap-2">
								<div className="flex items-center">
									<Label htmlFor="password">Description</Label>
									<Link
										href="#"
										className="ml-auto inline-block text-sm underline-offset-4 hover:underline"
									>
										Forgot your password?
									</Link>
								</div>
								<Input
									id="password"
									name="password"
									type="password"
									value={user.password}
									onChange={handleChange}
									required
								/>
							</div> */}
							<Button type="submit" className="w-full">
								Submit
							</Button>
							<Button
								onClick={handleCancel}
								variant="outline"
								className="w-full"
							>
								Cancel
							</Button>
						</div>
						{/* <div className="mt-4 text-center text-sm">
							Don&apos;t have an account?{" "}
							<Link to="/signup" className="underline underline-offset-4">
								Sign up
							</Link>
						</div> */}
					</form>
				</CardContent>
			</Card>
		</div>
	);
}
