
"use client"
import { useEffect, useState } from "react"
import Pagination from "../Components/Common/Pagination";
import SearchForm from "../Components/Common/SearchForm";


export default function Hospitals() {
    const [hospitals, setHospitals] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [size, setSize] = useState(5);
    const [totalPages, setTotalPages] = useState(0);
    const [districts, setDistricts] = useState([]);
    const [hospitalType, setHospitalType] = useState('');
    const [district, setDistrict] = useState('');

    const getHospitals = async () => {
        try {
            const queryParams = new URLSearchParams({
                districtId: district, 
                page: currentPage,
                size: size
              });

            const res = await fetch(`http://localhost:8080/api/hospitals?${queryParams.toString()}`);
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        } catch (error) {
            console.error("Error fetching data:", error);
            return { hospitals: [] };
        }
    };

    const getDistricts = async () => {
        try {
            const res = await fetch("http://localhost:8080/api/districts");
            if (!res.ok) {
                throw new Error("Network response was not ok");
            }
            return res.json();
        } catch (error) {
            console.error("Error fetching data:", error);
            return [];
        }
    };

    useEffect(() => {
        const fetchData = async () => {
            const data = await getHospitals();
            setHospitals(data.hospitals);
            setTotalPages(data.totalPages);
        };

        fetchData();
    }, [district, currentPage, size]);

    useEffect(() => {
        const fetchDistricts = async () => {
            const data = await getDistricts();
            setDistricts(data);
        };

        fetchDistricts();
    }, []);
    
    return (
        <div>
            <div className="mx-auto grid grid-cols-12 gap-4 bg-zinc-50 p-1">

                <div className="col-span-12 rounded-lg p-16 sm:col-span-3">
                <SearchForm
                    districts={districts}
                    districtId={district}
                    hospitalType={hospitalType}
                    setDistrict={setDistrict}
                    setHospitalType={setHospitalType}
                    onPageChange={setCurrentPage}/>
                </div>
                <div className="col-span-12 rounded-lg p-10 sm:col-span-9">
                    <table className="w-full border-collapse bg-white text-left text-sm text-gray-500">
                        <thead className="bg-gray-50">
                            <tr>
                                <th scope="col" className="px-3 py-3 font-medium text-gray-900">Sl.</th>
                                <th scope="col" className="px-3 py-3 font-medium text-gray-900">Name</th>
                                <th scope="col" className="px-3 py-3 font-medium text-gray-900">Address</th>
                                <th scope="col" className="px-3 py-3 font-medium text-gray-900">Hospital Type</th>
                                <th scope="col" className="px-3 py-3 font-medium text-gray-900">Organization Type</th>
                                <th scope="col" className="px-3 py-3 font-medium text-gray-900">Capacity(bed)</th>
                                {/* <th scope="col" className="px-3 py-3 font-medium text-gray-900"></th> */}
                            </tr>
                        </thead>
                        <tbody className="divide-y divide-gray-100 border-t border-gray-100">
                            {hospitals.map((hospital) =>
                                <tr id={hospital.id} className="hover:bg-gray-50">
                                    <td className="px-3 py-3">{hospital.id}</td>
                                    <th className="flex gap-3 px-3 py-3 font-normal text-gray-900">
                                        <div className="relative h-10 w-10">
                                            <img
                                                className="h-full w-full rounded-full object-cover object-center"
                                                src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                                                alt=""
                                            />
                                            <span className="absolute right-0 bottom-0 h-2 w-2 rounded-full bg-green-400 ring ring-white"></span>
                                        </div>
                                        <div className="text-sm">
                                            <div className="font-medium text-gray-700">{hospital.name}</div>
                                            <div className="text-gray-400">{hospital.bnName}</div>
                                        </div>
                                    </th>
                                    <td className="px-3 py-3">
                                        <div className="text-sm font-medium text-gray-700">{hospital.union?.bnName + ", " + hospital.upazila?.bnName + ", " +
                                            hospital.district?.bnName + ", " + hospital.district?.division?.bnName}</div>
                                    </td>
                                    <td className="px-3 py-3">{hospital.hospitalType?.benglaName}</td>
                                    <td className="px-3 py-3">
                                        {hospital.organizationType?.benglaName}
                                    </td>
                                    <td className="px-3 py-3">{hospital.numberOfBed}</td>
                                </tr>)}
                        </tbody>
                    </table>
                    <Pagination
                        currentPage={currentPage}
                        totalPages={totalPages}
                        onPageChange={setCurrentPage}
                        size={size}
                        onSizeChange={setSize} />
                </div>
            </div>
        </div>

    );
}