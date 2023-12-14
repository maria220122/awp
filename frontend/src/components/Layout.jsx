import React from 'react';
import { NavLink } from 'react-router-dom';
import '../App.css';

const Layout = ({children}) => {
  return (
    <div className="App">
      <div className="App__Aside"></div>
      <div className="App__Form">
        <div className="PageSwitcher">
          <NavLink
            to="/sign-in"
            activeClassName="PageSwitcher__Item--Active"
            className="PageSwitcher__Item"
          >
            Войти
          </NavLink>
          <NavLink
            exact
            to="/"
            activeClassName="PageSwitcher__Item--Active"
            className="PageSwitcher__Item"
          >
            Зарегистрироваться
          </NavLink>
        </div>

        <div className="FormTitle">
          <NavLink
            to="/sign-in"
            activeClassName="FormTitle__Link--Active"
            className="FormTitle__Link"
          >
            Войти
          </NavLink>{' '}
          или{' '}
          <NavLink
            exact
            to="/"
            activeClassName="FormTitle__Link--Active"
            className="FormTitle__Link"
          >
            Зарегистрироваться
          </NavLink>
        </div>

          {children}

      </div>
    </div>
  );
};

export default Layout;
