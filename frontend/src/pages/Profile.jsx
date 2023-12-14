import React, { Component } from 'react';
import '../App.css';
import { NavLink } from 'react-router-dom';
import ProfileCard from "../components/ProfileCard";

class Profile extends Component {
  constructor() {
    super();
  }
  render() {
    const data = this.props.location.state.data
    return (
      <div className="App">
        <div className="App__Aside" style={{ width: '100%' }}>
          <ProfileCard name={data.name} username={data.username} followers={2} likes={3} photos={1}/>

          <div className="PageSwitcher">
            <NavLink
              to="/sign-in"
              activeClassName="PageSwitcher__Item--Active"
              className="PageSwitcher__Item"
              style={{color: "white", margin: '59px'}}
            >
              Выйти
            </NavLink>
          </div>
        </div>
      </div>
    );
  }
}

export default Profile;
