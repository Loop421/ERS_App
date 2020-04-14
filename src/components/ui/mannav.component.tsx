import React from 'react';
import { Link } from 'react-router-dom';

const ManNavComponent: React.FC = () =>
{
    return (
        <nav>
            <ul>
                <li><Link id="item" to="/manager">Home</Link></li>
                <li><Link id="item" to="/reimbursement">Reimbursement</Link></li>
                <li><Link id="item" to="/allemployess">AllEmployees</Link></li>
                <li><Link to="/">Logout</Link></li>
            </ul>
        </nav>
    )
}

export default ManNavComponent;