import Image from 'next/image'

export default function Navbar () {
    return (
        <header class="w-full mt-5 text-gray-700 bg-white border-t border-gray-100 shadow-sm body-font">
        <div class="container flex flex-col items-start justify-between p-6 mx-auto md:flex-row">
            <a class="flex items-center mb-4 font-medium text-gray-900 title-font md:mb-0">
            <Image
            src="/logo.svg"
            alt="Next.js Logo"
            width={30}
            height={0}
            priority
        />
            <Image
            src="/open_care.svg"
            alt="Next.js Logo"
            width={150}
            height={0}
            priority
        />
            </a>
            <nav class="flex flex-wrap items-center justify-center pl-24 text-base md:ml-auto md:mr-auto">
                <a href="/" class="mr-5 font-medium hover:text-white hover:bg-gray-500 bg-black text-white p-2">Home</a>
                <a href="/hospitals" class="mr-5 font-medium hover:text-white hover:bg-gray-500 p-2">Hospitals</a>
                <a href="/doctors" class="mr-5 font-medium hover:text-white hover:bg-gray-500 p-2">Doctors</a>
                <a href="/institutes" class="font-medium hover:text-white hover:bg-gray-500 p-2">Institutes</a>
            </nav>
            <div class="items-center h-full">
                <a href="#_" class="mr-5 font-medium hover:text-gray-900">Login</a>
                <a href="#_"
                    class="px-4 py-2 text-xs font-bold text-white uppercase transition-all duration-150 bg-teal-500 rounded shadow outline-none active:bg-teal-600 hover:shadow-md focus:outline-none ease">
                    Sign Up
                </a>
            </div>
        </div>
    </header>
    );
}