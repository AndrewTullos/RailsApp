import React from "react";
import { Link } from "react-router-dom";

import { Plus, Bell, Search, Video, User } from "lucide-react";

import { Button } from "@/components/ui/button";
import {
	DropdownMenu,
	DropdownMenuContent,
	DropdownMenuGroup,
	DropdownMenuItem,
	DropdownMenuLabel,
	DropdownMenuPortal,
	DropdownMenuSeparator,
	DropdownMenuShortcut,
	DropdownMenuSub,
	DropdownMenuSubContent,
	DropdownMenuSubTrigger,
	DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Input } from "@/components/ui/input";

import { SignOut } from "./SignOut";

function HeaderTwo({ loggedInUser, setLoggedInUser }) {
	return (
		<header className="w-full sticky top-0 z-10 border-b bg-primary text-white">
			<div className="container mx-auto flex h-14 items-center">
				<Link to="/dashboard" className="flex items-center gap-2 font-semibold">
					<Video className="h-6 w-6" />
					<span className="text-xl font-bold">Rails App</span>
				</Link>
				<div className="ml-auto flex items-center gap-4">
					<div className="relative hidden md:block">
						<Search className="absolute left-2.5 top-2.5 h-4 w-4 text-muted-foreground" />
						<Input
							type="search"
							placeholder="Search clips..."
							className="w-[200px] pl-8 md:w-[300px] lg:w-[400px]"
						/>
					</div>
					<Button variant="outline" size="icon" className="relative">
						<Bell stroke="black" className="h-5 w-5" />
						<span className="absolute -right-1 -top-1 flex h-5 w-5 items-center justify-center rounded-full bg-primary text-[10px] text-primary-foreground">
							3
						</span>
					</Button>
					<Button variant="default" size="sm" className="gap-1 bg-indigo-700">
						<Plus className="h-4 w-4" />
						<span className="hidden sm:inline">New Clip</span>
					</Button>

					<DropdownMenu>
						<DropdownMenuTrigger asChild>
							<Avatar className="h-8 w-8 cursor-pointer">
								<AvatarImage
									src="/placeholder.svg?height=32&width=32"
									alt="@user"
								/>
								<AvatarFallback>
									<User stroke="black" className="h-5 w-5" />
								</AvatarFallback>
							</Avatar>
						</DropdownMenuTrigger>
						<DropdownMenuContent className="w-56">
							<DropdownMenuLabel>My Account</DropdownMenuLabel>
							<DropdownMenuSeparator />
							<DropdownMenuGroup>
								<DropdownMenuItem>
									Profile
									<DropdownMenuShortcut>⇧⌘P</DropdownMenuShortcut>
								</DropdownMenuItem>

								<DropdownMenuItem>
									Settings
									<DropdownMenuShortcut>⌘S</DropdownMenuShortcut>
								</DropdownMenuItem>
							</DropdownMenuGroup>

							<DropdownMenuSeparator />
							<DropdownMenuItem>
								<Link to={"https://github.com/AndrewTullos/"}>GitHub</Link>
							</DropdownMenuItem>
							<DropdownMenuItem>Support</DropdownMenuItem>
							<DropdownMenuItem disabled>API</DropdownMenuItem>
							<DropdownMenuSeparator />
							<DropdownMenuItem>
								<SignOut />
								<DropdownMenuShortcut>⇧⌘Q</DropdownMenuShortcut>
							</DropdownMenuItem>
						</DropdownMenuContent>
					</DropdownMenu>
				</div>
			</div>
		</header>
	);
}

export default HeaderTwo;
