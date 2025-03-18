import { Link } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

import HeaderTwo from "@/components/HeaderTwo";

function DashboardPage() {
	return (
		<div className="min-h-screen bg-primary text-white">
			<HeaderTwo />
		</div>
	);
}

export default DashboardPage;
