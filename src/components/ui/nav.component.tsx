import React from 'react';
import { Link } from 'react-router-dom';

const NavComponent: React.FC = () =>
{
    return (
        <nav>
            <ul>
                <li><Link id="item" to="/welcome">Home</Link></li>
                <li><Link id="item" to="/profile">Proflie</Link></li>
                <li><Link to="/empReim">Reimbursement</Link></li>
                <li><Link to="/">Logout</Link></li>
            </ul>
        </nav>
    )
}

export default NavComponent;