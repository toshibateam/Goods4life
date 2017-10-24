import React, { Component } from 'react';
import { StatusBar, Navigator } from 'react-native';

import NavigationExperimental from 'react-native-deprecated-custom-components';


import Authentication from './Authentication/Authentication';
import ChangeInfo from './ChangeInfo/ChangeInfo';
import Main from './Main/Main';
import OrderHistory from './OrderHistory/OrderHistory';
import ForgotPW from './Authentication/ForgotPW'
//import refreshToken from '../api/refreshToken';

StatusBar.setHidden(true);

export default class App extends Component {
    componentDidMount() {
       // setInterval(refreshToken, 30000);
    }
    render() {
        return (
            <NavigationExperimental.Navigator 
                initialRoute={{ name: 'MAIN' }}
                renderScene={(route, navigator) => {
                    switch (route.name) {
                        case 'MAIN': return <Main navigator={navigator} />;
                        case 'CHANGE_INFO': return <ChangeInfo navigator={navigator} user={route.user} />;
                        case 'AUTHENTICATION': return <Authentication navigator={navigator} />;
                        case 'ORDER_HISTORY': return <OrderHistory navigator={navigator} />;
                        default: return <ForgotPW navigator={navigator} />;
                    }
                }}
                configureScene={route => {
                    if (route.name === 'AUTHENTICATION') return NavigationExperimental.Navigator.SceneConfigs.FloatFromRight;
                    return NavigationExperimental.Navigator.SceneConfigs.FloatFromLeft;
                }}
            />
        );
    }
}
