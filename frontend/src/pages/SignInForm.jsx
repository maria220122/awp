import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import Layout from '../components/Layout';

class SignInForm extends Component {
  constructor() {
    super();

    this.state = {
      username: '',
      password: '',
      rememberMe: false,
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(e) {
    let target = e.target;
    let value = target.type === 'checkbox' ? target.checked : target.value;
    let name = target.name;

    this.setState({
      [name]: value,
    });
  }

  handleSubmit(e, push) {
    e.preventDefault();

    fetch('http://localhost:8080/api/login', {
      method: 'POST',
      body: `username=${this.state.username}&password=${this.state.password}&remember-me=${this.state.rememberMe}`,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    })
      .then(res => res.json())
      .then(data => {
        push({ pathname: '/profile', state: { data } });
      })
      .catch(() => alert('Введены неправильные данные'));
  }

  render() {
    const {
      history: { push },
    } = this.props;

    return (
      <Layout>
        <div className="FormCenter">
          <form
            onSubmit={e => this.handleSubmit(e, push)}
            className="FormFields"
          >
            <div className="FormField">
              <label className="FormField__Label" htmlFor="username">
                Логин
              </label>
              <input
                type="text"
                id="username"
                className="FormField__Input"
                placeholder="Введите логин"
                name="username"
                value={this.state.email}
                onChange={this.handleChange}
              />
            </div>

            <div className="FormField">
              <label className="FormField__Label" htmlFor="password">
                Пароль
              </label>
              <input
                type="password"
                id="password"
                className="FormField__Input"
                placeholder="Введите пароль"
                name="password"
                value={this.state.password}
                onChange={this.handleChange}
              />
            </div>

            <div className="FormField">
              <label className="FormField__CheckboxLabel">
                <input
                  className="FormField__Checkbox"
                  type="checkbox"
                  name="rememberMe"
                  value={this.state.rememberMe}
                  onChange={this.handleChange}
                />{' '}
                Запомнить меня
              </label>
            </div>

            <div className="FormField">
              <button className="FormField__Button mr-20">Войти</button>{' '}
              <Link to="/" className="FormField__Link">
                Создать аккаунт
              </Link>
            </div>
          </form>
        </div>
      </Layout>
    );
  }
}

export default withRouter(SignInForm);
