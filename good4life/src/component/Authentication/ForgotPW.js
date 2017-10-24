import React, { Component } from 'react';
import { View, TextInput, Text, TouchableOpacity, StyleSheet, Alert, Image } from 'react-native';
import register from '../../api/register';

import icBack from '../../media/appIcon/back_white.png';
import icLogo from '../../media/appIcon/ic_logo.png';

export default class ForgotPW extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            repassword: ''
        };
    }

    onSuccess() {
        Alert.alert(
            'Notice',
            'Sign up successfully',
            [
                { text: 'OK', onPress: this.props.gotoSignIn() }
            ],
            { cancelable: false }
        );
    }

    onFail() {
        Alert.alert(
            'Notice',
            'Failllll',
            [
                // { text: 'OK', onPress: () => this.removeEmail.bind(this) }
            ],
            { cancelable: false }
        );
    }

    recoverPW() {
        const { email, password, repassword } = this.state;
        // register(email, username, password)
        // .then(res => {
        //     if (res === 'THANH_CONG') return this.onSuccess();
        //     this.onFail();
        // });
    }

    goBackToMain() {
        const { navigator } = this.props;
        navigator.pop();
    }

    render() {
        const { inputStyle, bigButton, buttonText, container, row1, iconStyle, titleStyle } = styles;
        return (
            <View style={container}>
                <View style={row1}>
                    <TouchableOpacity onPress={this.goBackToMain.bind(this)}>
                        <Image source={icBack} style={iconStyle} />
                    </TouchableOpacity>
                    <Text style={titleStyle}>Recover Password</Text>
                    <Image source={icLogo} style={iconStyle} />
                </View>
                <View>
                <TextInput 
                    style={inputStyle} 
                    placeholder="Enter your email" 
                    value={this.state.email}
                    onChangeText={text => this.setState({ email: text })}
                />
                <TextInput 
                    style={inputStyle} 
                    placeholder="Enter your password" 
                    value={this.state.password}
                    secureTextEntry
                    onChangeText={text => this.setState({ password: text })}
                />
                <TextInput 
                    style={inputStyle} 
                    placeholder="Re-enter your password" 
                    value={this.state.rePassword}
                    secureTextEntry
                    onChangeText={text => this.setState({ repassword: text })}
                />
                <TouchableOpacity style={bigButton} onPress={this.recoverPW.bind(this)}>
                    <Text style={buttonText}>Submit</Text>
                </TouchableOpacity>
                </View>
                <View/>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#3EBA77',
        padding: 20,
        justifyContent: 'space-between',
    },
    row1: { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' },
    iconStyle: { width: 30, height: 30 },
    titleStyle: { color: '#FFF', fontFamily: 'Avenir', fontSize: 30 },
    inputStyle: {
        height: 50,
        backgroundColor: '#fff',
        marginBottom: 10,
        borderRadius: 20,
        paddingLeft: 30
    },
    bigButton: {
        height: 50,
        borderRadius: 20,
        borderWidth: 1,
        borderColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
        marginTop: 10
    },
    buttonText: {
        fontFamily: 'Avenir',
        color: '#fff',
        fontWeight: '400'
    }
});
