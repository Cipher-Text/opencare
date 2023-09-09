"use client"
import Link from 'next/link'
import Image from 'next/image'
import { usePathname } from 'next/navigation'

export default function Navbar () {
    const pathname = usePathname();

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
                <Link href="/" className={`mr-5 font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/' ? 'bg-black text-white' : 'bg-red'}`}>Home</Link>
                <Link href="/hospitals" className={`mr-5 font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/hospitals' ? 'bg-black text-white' : ''}`}>Hospitals</Link>
                <Link href="/doctors" className={`mr-5 font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/doctors' ? 'bg-black text-white' : ''}`}>Doctors</Link>
                <Link href="/institutes" className={`font-medium hover:text-white hover:bg-gray-500 p-2 ${pathname === '/institutes' ? 'bg-black text-white' : ''}`}>Institutes</Link>
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