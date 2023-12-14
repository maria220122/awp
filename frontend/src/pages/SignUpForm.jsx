import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import Layout from '../components/Layout';

class SignUpForm extends Component {
  constructor() {
    super();

    this.state = {
      username: '',
      password: '',
      name: '',
      hasAgreed: false,
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

    fetch('http://localhost:8080/api/users/register', {
      method: 'POST',
      body: JSON.stringify({
        username: this.state.username,
        name: this.state.name,
        password: this.state.password,
      }),
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then(() => {
        push({
          pathname: '/profile',
          state: {
            data: { username: this.state.username, name: this.state.name },
          },
        });
      })
      .then(() => alert('Регистрация успешна!'))
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
              <label className="FormField__Label" htmlFor="name">
                Имя
              </label>
              <input
                type="text"
                id="name"
                className="FormField__Input"
                placeholder="Введите имя"
                name="name"
                value={this.state.name}
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
                  name="hasAgreed"
                  value={this.state.hasAgreed}
                  onChange={this.handleChange}
                />{' '}
                Я согласен с{' '}
                <a
                  href=""
                  onClick={() => alert('You died')}
                  className="FormField__TermsLink"
                >
                  условиями обслуживания
                </a>
              </label>
            </div>

            <div className="FormField">
              <button className="FormField__Button mr-20">
                Зарегистрироваться
              </button>{' '}
              <Link to="/sign-in" className="FormField__Link">
                Я уже зарегистрирован
              </Link>
            </div>
          </form>
        </div>
      </Layout>
    );
  }
}

export default withRouter(SignUpForm);
