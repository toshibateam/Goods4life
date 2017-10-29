import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,TouchableOpacity
} from 'react-native';

import ScrollableTabView, {DefaultTabBar} from 'react-native-scrollable-tab-view';
import Purchase from './Purchase';
import Sell from './Sell';

class Contact extends Component {
  render() {
    return (
      <ScrollableTabView
        style={{backgroundColor: '#fff', }}
        renderTabBar={() => <DefaultTabBar />}
        tabBarUnderlineStyle = {{backgroundColor: '#34B089'}}
        tabBarTextStyle = {{fontFamily: 'Avenir', fontSize: 14}}
      >
        <Purchase tabLabel="Purchase" />
        <Sell tabLabel="Sell" />
      </ScrollableTabView>
    );
  }
}

export default Contact;
