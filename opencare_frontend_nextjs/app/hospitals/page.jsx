const getHospitals = async () => {
    const res = await fetch("http://localhost:8080/api/hospitals");
    return res.json();
}
export default async function Hospitals(){
    const response = await getHospitals();
    return (
        <div>
            {response.hospitals.map((hospital) =>
    <li>{hospital.name}</li>)}
        </div>
    );
}