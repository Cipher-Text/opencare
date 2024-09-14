import {useNavigate} from "react-router-dom";
import {useState} from "react";
import toast from "react-hot-toast";
import {Modal} from "react-bootstrap"

const TeacherPersonalInfoPage = () => {
    const [show, setShow] = useState(false);
    const handleClose = () =>   setShow(false);

    const updateSave = () => {
        toast.success('Update saved!');
        handleClose();
    }
    const navigate = useNavigate();
    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-md-8">
                    <h3>Personal Information</h3>
                    <div className="col-12">
                        <ul className="list-group info-list">
                            <li className="list-group-item">
                                <div className="row">
                                    <div className="col-6">
                                        <strong>Name:</strong>
                                    </div>
                                    <div className="col-6">
                                        fdfsdf
                                    </div>
                                </div>
                            </li>

                            <li className="list-group-item">
                                <div className="row">
                                    <div className="col-6">
                                        <strong>Father's Name:</strong>
                                    </div>
                                    <div className="col-6">
                                        fsdfs
                                    </div>
                                </div>
                            </li>

                            <li className="list-group-item">
                                <div className="row">
                                    <div className="col-6">
                                        <strong>Mother's Name:</strong>
                                    </div>
                                    <div className="col-6">
                                        fdfs
                                    </div>
                                </div>
                            </li>

                            <li className="list-group-item">
                                <div className="row">
                                    <div className="col-6">
                                        <strong>Address:</strong>
                                    </div>
                                    <div className="col-6">
                                        fdfs
                                    </div>
                                </div>
                            </li>

                            <li className="list-group-item">
                                <div className="row">
                                    <div className="col-6">
                                        <strong>Sex:</strong>
                                    </div>
                                    <div className="col-6">
                                        fdfs
                                    </div>
                                </div>
                            </li>

                            <li className="list-group-item">
                                <div className="row">
                                    <div className="col-6">
                                        <strong>Email:</strong>
                                    </div>
                                    <div className="col-6">
                                        fdfs
                                    </div>
                                </div>
                            </li>

                            <li className="list-group-item">
                                <div className="row">
                                    <div className="col-6">
                                        <strong>Mobile:</strong>
                                    </div>
                                    <div className="col-6">
                                        fdfs
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div className="row">
                        <div className="d-flex justify-content-start mt-3 gap-2">
                            <button className="btn btn-success" onClick={() => navigate('/teacher-info-update')}>
                                Update Info
                            </button>
                            <button className="btn btn-success" onClick={() => setShow(true)}>
                                Change Password
                            </button>
                        </div>
                    </div>

                </div>
                <div className="col-md-3 col-12 d-flex justify-content-center align-items-center mt-5">
                    <div className="text-center">
                        <img
                            src="https://via.placeholder.com/250"
                            alt="Teacher's Image"
                            className="rounded-circle mb-3 img-fluid"
                            style={{maxWidth: '100%', height: 'auto'}}
                        />
                        <div className="mb-3">
                            <input className="form-control" type="file" id="formFile"/>
                        </div>
                    </div>
                </div>

            </div>


            <Modal className="rounded-2" show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title className="h6">Change Password</Modal.Title>
                </Modal.Header>
                <Modal.Body className="m-0 p-0">
                    <div className="container-fluid m-0 px-4 py-3">
                        <div className="row">

                            <div className="col-md-12">
                                <label className="form-label">Old Password</label>
                                <input type="text" className="form-control" id="oldpassword"
                                       placeholder="Enter Old Password"/>
                            </div>
                            <div className="col-md-12">
                                <label className="form-label">New Password</label>
                                <input type="text" className="form-control" id="newpassword"
                                       placeholder="Enter New Passsword"/>
                            </div>
                            <div className="col-md-12">
                                <label className="form-label">Confirm Password</label>
                                <input type="text" className="form-control" id="confirmpassword"
                                       placeholder="Enter Confirm Password"/>
                            </div>
                        </div>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <button className="btn btn-sm btn-danger" onClick={handleClose}>Close</button>
                    <button className="btn btn-sm btn-success" onClick={updateSave}>Save</button>
                </Modal.Footer>
            </Modal>
        </div>
    );
};

export default TeacherPersonalInfoPage;