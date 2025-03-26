import Github from "../assets/github.png";
import LinkedIn from "../assets/Linkedin.png";
import { useNavigate, Link } from "react-router-dom";

const people = [
	{
		name: "Drew Tullos",
		role: "Full Stack Engineer",
		imageUrl:
			"https://rails-app-capstone.s3.us-west-1.amazonaws.com/Headshot.jpg",
		bio: "Junior FullStack engineer. \nJava, MySQL, React",
		xUrl: "https://github.com/AndrewTullos/",
		linkedinUrl: "https://www.linkedin.com/in/andrewtullos/",
	},
];

export default function About() {
	return (
		<div className="bg-transparent py-24 md:py-32">
			<div className="mx-auto grid max-w-7xl grid-cols-1 gap-20 px-6 lg:px-8 xl:grid-cols-5">
				<div className="max-w-2xl xl:col-span-2">
					<h2 className="text-pretty text-4xl font-semibold tracking-tight text-primary sm:text-5xl">
						About Rails
					</h2>
					<p className="mt-6 text-lg/8 text-primary">
						Rails is a social media app, built for skateboarders by
						skateboarders. It's goal is to promote the sport and the community
						by bringing users together by location with an ability to connect
						and share their experiences.
					</p>
				</div>
				<ul role="list" className="divide-y divide-gray-200 xl:col-span-3">
					{people.map((person) => (
						<li
							key={person.name}
							className="flex flex-col gap-10 py-12 first:pt-0 last:pb-0 sm:flex-row"
						>
							<img
								alt=""
								src={person.imageUrl}
								width={500}
								height={500}
								className="aspect-[4/5] w-52 flex-none rounded-2xl object-cover"
							/>
							<div className="max-w-xl flex-auto">
								<h3 className="text-lg/8 font-semibold tracking-tight text-primary">
									{person.name}
								</h3>
								<p className="text-base/7 text-primary">{person.role}</p>
								<p className="mt-6 text-base/7 text-primary">{person.bio}</p>
								<ul role="list" className="mt-6 flex gap-x-6">
									<li>
										<a
											href={person.githubUrl}
											className="text-primary hover:text-indigo-600"
										>
											<span className="sr-only">Github</span>
											<img width={100} height={100} src={Github} alt="" />
											{/* <svg
												fill="currentColor"
												viewBox="0 0 20 20"
												aria-hidden="true"
												className="size-5"
											>
												<path d="M11.4678 8.77491L17.2961 2H15.915L10.8543 7.88256L6.81232 2H2.15039L8.26263 10.8955L2.15039 18H3.53159L8.87581 11.7878L13.1444 18H17.8063L11.4675 8.77491H11.4678ZM9.57608 10.9738L8.95678 10.0881L4.02925 3.03974H6.15068L10.1273 8.72795L10.7466 9.61374L15.9156 17.0075H13.7942L9.57608 10.9742V10.9738Z" />
											</svg> */}
										</a>
									</li>
									<li>
										<a
											href={person.linkedinUrl}
											className="text-primary hover:text-indigo-600"
										>
											<span className="sr-only">LinkedIn</span>
											<img width={100} height={100} src={LinkedIn} alt="" />

											{/* <svg
												fill="currentColor"
												viewBox="0 0 20 20"
												aria-hidden="true"
												className="size-5"
											>
												<path
													d="M16.338 16.338H13.67V12.16c0-.995-.017-2.277-1.387-2.277-1.39 0-1.601 1.086-1.601 2.207v4.248H8.014v-8.59h2.559v1.174h.037c.356-.675 1.227-1.387 2.526-1.387 2.703 0 3.203 1.778 3.203 4.092v4.711zM5.005 6.575a1.548 1.548 0 11-.003-3.096 1.548 1.548 0 01.003 3.096zm-1.337 9.763H6.34v-8.59H3.667v8.59zM17.668 1H2.328C1.595 1 1 1.581 1 2.298v15.403C1 18.418 1.595 19 2.328 19h15.34c.734 0 1.332-.582 1.332-1.299V2.298C19 1.581 18.402 1 17.668 1z"
													clipRule="evenodd"
													fillRule="evenodd"
												/>
											</svg> */}
										</a>
									</li>
								</ul>
							</div>
						</li>
					))}
				</ul>
			</div>
			<div className="mt-10 flex items-center justify-center gap-x-6">
				<Link
					type="button"
					to={`/`}
					className="rounded-md bg-indigo-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
				>
					Go back home
				</Link>
			</div>
		</div>
	);
}
