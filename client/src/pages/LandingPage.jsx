import React from "react";
import { Link } from "react-router-dom";

import HeaderOne from "@/components/HeaderOne";
import Hero from "@/components/Hero";
import Footer from "@/components/Footer";

import {
	NavigationMenu,
	NavigationMenuList,
	NavigationMenuLink,
} from "@/components/ui/navigation-menu";

// import { Hero } from "@/components/Hero";

function LandingPage() {
	return (
		<>
			<HeaderOne />
			<Hero />
			<Footer />
		</>
	);
}

export default LandingPage;
