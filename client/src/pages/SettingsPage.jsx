import { useState, useEffect } from "react";

import { Link, useNavigate } from "react-router-dom";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

import { Video, GalleryVerticalEnd } from "lucide-react";

// ShadCN
import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar";

// My Components
import HeaderTwo from "@/components/HeaderTwo";
import { AppSidebar } from "@/components/AppSidebar";
import getUserIdFromToken from "../functions/getToken";
import Footer from "../components/Footer";

import Feed from "@/components/Feed";
import UserSuggestions from "@/components/UserSuggestions";

export default function SettingsPage({
	className,
	loggedInUser,
	setLoggedInUser,
	...props
}) {
	const userId = getUserIdFromToken(loggedInUser);

	const navigate = useNavigate();

	const [user, setUser] = useState({
		username: "",
		email: "",
		password: "",
		profilePicture: null,
		firstName: "",
		lastName: "",
		city: "",
		state: "",
		postalCode: "",
	});
	const [file, setFile] = useState(null);
	const [errors, setErrors] = useState([]);

	useEffect(() => {
		const fetchUser = async () => {
			try {
				const response = await fetch(
					`http://localhost:8080/api/user/${userId}`
				);
				if (response.ok) {
					const data = await response.json();
					setUser(data);
				} else {
					const errorData = await response.json();
					setErrors([errorData.message || "Failed to fetch user data."]);
				}
			} catch (err) {
				setErrors([err.message || "An error occurred."]);
				console.error("Fetch Error:", err);
			}
		};

		fetchUser();
	}, [userId]);

	const handleSubmit = (event) => {
		event.preventDefault();

		if (!file) {
			setErrors(["Please select a file to upload"]);
			return;
		}

		if (!userId) {
			setErrors(["User ID not found. Please log in again."]);
			return;
		}

		const formData = new FormData();
		formData.append("file", file);

		const metadata = {
			userId: userId,
			username: user.username,
			firstName: user.firstName,
			lastName: user.lastName,
			profilePicture: user.profilePicture,
			city: user.city,
			state: user.state,
		};

		// Convert metadata to a Blob with JSON content type
		const metadataBlob = new Blob([JSON.stringify(metadata)], {
			type: "application/json",
		});

		formData.append("metadata", metadataBlob);

		fetch("http://localhost:8080/api/v1/s3/profile/picture", {
			method: "POST",
			body: formData,
		})
			.then((response) => {
				if (response.status >= 200 && response.status < 300) {
					return response.json().then((data) => {
						navigate("/dashboard");
					});
				} else {
					return response.text().then((text) => {
						setErrors(["Upload failed: " + text]);
					});
				}
			})
			.catch((error) => {
				console.error("Upload error:", error);
				setErrors(["An error occurred during upload"]);
			});
	};

	const handleChange = (event) => {
		const { name, value, files } = event.target;

		if (event.target.type === "file") {
			const selectedFile = files[0];
			setFile(selectedFile);
		} else {
			setUser((prevUser) => ({
				...prevUser,
				[name]: value,
			}));
		}
	};

	function handleCancel() {
		navigate("/dashboard");
	}
	return (
		<SidebarProvider>
			<AppSidebar
				loggedInUser={loggedInUser}
				className="bg-primary text-secondary"
			/>

			<div className="min-h-screen w-full text-white flex flex-col ">
				<HeaderTwo
					loggedInUser={loggedInUser}
					setLoggedInUser={setLoggedInUser}
				/>

				<div className="flex justify-center items-center content-center gap-4 rounded m-7">
					<div>
						<form
							onSubmit={handleSubmit}
							className={cn("flex flex-col gap-6 text-amber-600", className)}
							{...props}
						>
							<div className="flex flex-col items-center gap-2 text-center">
								{errors.length > 0 && (
									<div className="text-red-500">
										{errors.map((error, index) => (
											<p key={index}>{error}</p>
										))}
									</div>
								)}
								<h1 className="text-2xl text-white font-bold">
									Update your account
								</h1>
							</div>
							<div className="grid gap-6">
								<div className="grid gap-2">
									<Label htmlFor="username">Username</Label>
									<Input
										className="text-white "
										id="username"
										name="username"
										type="username"
										value={user.username}
										placeholder="Enter your username..."
										onChange={handleChange}
										required
									/>
								</div>

								<div className="flex gap-4">
									{" "}
									<div className="grid gap-2 flex-1">
										{" "}
										<Label htmlFor="firstName">First Name</Label>
										<Input
											className="text-white "
											id="firstName"
											name="firstName"
											type="firstName"
											value={user.firstName}
											placeholder="Joe"
											onChange={handleChange}
											required
										/>
										<Label htmlFor="city">City</Label>
										<Input
											className="text-white "
											id="city"
											name="city"
											type="city"
											value={user.city}
											placeholder="City"
											onChange={handleChange}
											required
										/>
									</div>
									<div className="grid gap-2 flex-1">
										<Label htmlFor="lastName">Last Name</Label>
										<Input
											className="text-white "
											id="lastName"
											name="lastName"
											type="lastName"
											value={user.lastName}
											placeholder="Schmoe"
											onChange={handleChange}
											required
										/>
										<Label htmlFor="state">State</Label>
										<Input
											className="text-white "
											id="state"
											name="state"
											type="state"
											value={user.state}
											placeholder="State"
											onChange={handleChange}
											required
										/>
									</div>
								</div>
								<div className="grid gap-2">
									<Label htmlFor="file">Profile Picture</Label>
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
									<Label htmlFor="postalCode">Zipcode</Label>
									<Input
										id="postalCode"
										name="postalCode"
										type="postalCode"
										placeholder="11111"
										value={user.postalCode}
										onChange={handleChange}
										required
									/>
								</div> */}

								{/* <div className="grid gap-2">
									<Label htmlFor="email">Email</Label>
									<Input
										id="email"
										name="email"
										type="email"
										value={user.email}
										placeholder="m@example.com"
										onChange={handleChange}
										required
									/>
								</div> */}

								{/* <div className="grid gap-2">
									<div className="flex items-center">
										<Label htmlFor="password">Password</Label>
									</div>
									<Input
										id="password"
										type="password"
										name="password"
										value={user.password}
										onChange={handleChange}
										required
									/>
								</div> */}
								<Button
									type="submit"
									className="w-full bg-amber-700 text-white hover:bg-black hover:text-white"
								>
									Update
								</Button>
								<Button type="submit" className="w-full" onClick={handleCancel}>
									Cancel
								</Button>
							</div>
						</form>
					</div>
				</div>
				{/* <Footer /> */}
			</div>
		</SidebarProvider>
	);
}
