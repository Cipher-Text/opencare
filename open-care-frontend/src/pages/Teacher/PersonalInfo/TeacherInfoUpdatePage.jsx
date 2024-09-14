import {useState} from "react";

const TeacherInfoUpdatePage = () => {
    const [date, setDate] = useState(new Date().toISOString().split("T")[0]);

    return (
        <div className="container mt-5">
            <div className="row">
                <form>
                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label htmlFor="teacherName" className="form-label">Full Name</label>
                            <input type="text" className="form-control" id="teacherName"
                                   placeholder="Enter name"/>
                        </div>

                        <div className="col-md-6">
                            <label htmlFor="fatherName" className="form-label">Father's Name</label>
                            <input type="text" className="form-control" id="fatherName"
                                   placeholder="Enter father's name"/>
                        </div>
                    </div>

                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label htmlFor="motherName" className="form-label">Mother's Name</label>
                            <input type="text" className="form-control" id="motherName"
                                   placeholder="Enter mother's name"/>
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="sex" className="form-label">Sex</label>
                            <select className="form-control form-select" id="sex">
                                <option value="">choose</option>
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                                <option value="other">Other</option>
                            </select>
                        </div>
                    </div>

                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label htmlFor="mobile" className="form-label">Mobile</label>
                            <input type="text" className="form-control" id="mobile" placeholder="Enter mobile number"/>
                        </div>

                        <div className="col-md-6">
                            <label htmlFor="email" className="form-label">Email Address</label>
                            <input type="email" className="form-control" id="email" placeholder="Enter email"/>
                        </div>
                    </div>

                    <div className="row mb-3">
                        <div className="col-md-6">
                            <label htmlFor="subject" className="form-label">Enrollment</label>
                            <input
                                type="date"
                                id="InputDate"
                                className="form-control form-control-sm animated zoomIn"
                                min="2012-01-12"
                                max="2050-01-01"
                                value={date}
                                placeholder="DD/MM/YYYY"
                                onChange={(e) => {
                                    setDate(e.target.value)
                                }}
                            />
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="subject" className="form-label">Subject</label>
                            <input type="text" className="form-control" id="subject" placeholder="Enter subject"/>
                        </div>
                    </div>
                    <div>
                        <div className="col-md-12">
                            <label htmlFor="address" className="form-label">Address</label>
                            <textarea type="text" className="form-control" id="address" placeholder="Enter address"/>
                        </div>
                    </div>

                    <button type="submit" className="btn btn-success mt-3">Save</button>
                </form>

            </div>
        </div>
    );
};

export default TeacherInfoUpdatePage;