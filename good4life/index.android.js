
import React, { Component } from 'react';
import {
  AppRegistry,
} from 'react-native';
import App from './src/component/App'

export default class good4life extends Component {
  render() {
    return (
      <App />
    );
  }
}

AppRegistry.registerComponent('good4life', () => good4life);
