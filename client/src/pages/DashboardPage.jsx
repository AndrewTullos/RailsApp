import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import HeaderTwo from "@/components/HeaderTwo";

function DashboardPage({ loggedInUser, setLoggedInUser }) {
	const navigate = useNavigate();

	useEffect(() => {
		if (!loggedInUser) {
			navigate("/login");
		}
	}, [loggedInUser, navigate]);

	return (
		<div className="min-h-screen w-full text-white flex flex-col">
			<HeaderTwo
				loggedInUser={loggedInUser}
				setLoggedInUser={setLoggedInUser}
			/>
		</div>
	);
}

export default DashboardPage;
