import React, { useState, useEffect } from 'react';
import Axios from 'axios';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import ManNavComponent from '../ui/mannav.component';

const EmployeesComponent: React.FC = () =>
{
    const [employees, setEmployees] = useState([]);


    useEffect(() =>{
        Axios.get('http://localhost:8080/Project1_war/api/employees/')
            .then(response =>{
                setEmployees(response.data)
            }).catch((err) =>{
                console.log(err)
            })
    },[]);

    return(
        <React.Fragment>
            <ManNavComponent></ManNavComponent>
            <h2>Employess</h2>
            <hr/>
            <br/>
            <div className='table'>
                <p className='table-header'>All Employees</p>
                <BootstrapTable data={employees}>
                    
                    <TableHeaderColumn isKey dataField='empid'>
                        ID
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='name'>
                        name
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='phoneNum'>
                        Phone
                    </TableHeaderColumn>
                    <TableHeaderColumn dataField='email'>
                        Email
                    </TableHeaderColumn>
                </BootstrapTable>
            </div>
        </React.Fragment>
    )
}

export default EmployeesComponent;