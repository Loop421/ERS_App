import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import NavComponent from '../ui/nav.component';
import InputComponent from '../ui/input.component';
import TableComponent from './table.component';

const EmployeeReimbursementComponet: React.FC = () =>
{
    const empid: any = localStorage.getItem('id');
    const [reimbursment, setReimbursement] = useState([]);

    useEffect(()=>{
        Axios.post('http://localhost:8080/Project1_war/api/reimbursements/emp', {
            empid
        }).then(response =>{
                setReimbursement(response.data);
            }).catch((err) =>{
                console.log(err);
            })
    },[]);

    const updateEmployeeReim = (newValue: any) =>
    {
        setReimbursement(newValue);
    }

    return(
        <React.Fragment>
            <NavComponent></NavComponent>
            <h2>Expense Reimbursements</h2>
            <hr/>
            <br/>
            <div className='table'>
                <TableComponent reimbursment={reimbursment}></TableComponent>
            </div>
            <InputComponent
                updateEmployeeReim={(newValue: any) => updateEmployeeReim(newValue)}
            ></InputComponent>
        </React.Fragment>
    )
}

export default EmployeeReimbursementComponet;