import {BrowserRouter, Routes, Route} from "react-router-dom";
import TeacherPersonalInfo from "../src/componet/Teacher/PersonalInfo/TeacherPersonalInfo.jsx";
import TeacherInfoUpdate from "../src/componet/Teacher/PersonalInfo/TeacherInfoUpdate.jsx";


const Web = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<TeacherPersonalInfo/>}/>
                <Route path="/teacher-info-update" element={<TeacherInfoUpdate/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default Web;