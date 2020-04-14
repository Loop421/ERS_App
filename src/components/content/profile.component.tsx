import React from 'react';
import NavComponent from '../ui/nav.component';

const ProfileComponent: React.FC = () =>
{
    return(
        <React.Fragment>
            <NavComponent></NavComponent>
            <h2>Personal Information</h2>
            <p>First Name: </p>
            <p>Last Name: </p>
            <p>Mobile: </p>
            <p>Email: </p>
        </React.Fragment>
    )
}

export default ProfileComponent;