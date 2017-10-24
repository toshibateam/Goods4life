import React, { Component } from 'react';
import { View, Text } from 'react-native';

class Notification extends Component {
    render() {
        return (
            <View style={{backgroundColor: '#F6F6F6', alignItems: 'center', justifyContent: 'center'}}>
                <Text>Notification component</Text>
            </View>
        );
    }
}

export default Notification;