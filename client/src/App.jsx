import { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import LandingPage from "./pages/LandingPage";
import NotFoundPage from "./pages/NotFoundPage";
import LoginPage from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import DashboardPage from "./pages/DashboardPage";

function App() {
	const [loggedInUser, setLoggedInUser] = useState(null);
	const [hasFinishedCheckingLocalStorage, setHasFinishedCheckingLocalStorage] =
		useState(false);

	useEffect(() => {
		if (localStorage.getItem("loggedInUser") !== undefined) {
			setLoggedInUser(JSON.parse(localStorage.getItem("loggedInUser")));
		}
		setHasFinishedCheckingLocalStorage(true);
	}, []);

	if (!hasFinishedCheckingLocalStorage) {
		return null;
	}

	return (
		<div>
			<Router>
				<Routes>
					<Route path="/" element={<LandingPage />} />
					<Route
						path="/login"
						element={
							<LoginPage
								loggedInUser={loggedInUser}
								setLoggedInUser={setLoggedInUser}
							/>
						}
					/>
					<Route path="/dashboard" element={<DashboardPage />} />
					<Route path="/signup" element={<SignupPage />} />
					<Route path="/*" element={<NotFoundPage />} />
				</Routes>
			</Router>
		</div>
	);
}

export default App;
