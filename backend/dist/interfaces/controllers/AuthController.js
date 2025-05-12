"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AuthController = void 0;
const AuthService_1 = require("@domain/services/AuthService");
const MongoUserRepository_1 = require("@infrastructure/database/repositories/MongoUserRepository");
const authService = new AuthService_1.AuthService(new MongoUserRepository_1.MongoUserRepository());
class AuthController {
    async register(req, res) {
        try {
            const { email, password, fullName } = req.body;
            const user = await authService.register({ email, password, fullName });
            res.status(201).json({ message: 'User registered successfully', user });
        }
        catch (error) {
            res.status(400).json({ message: (error === null || error === void 0 ? void 0 : error.message) || 'Registration failed' });
        }
    }
    async login(req, res) {
        try {
            const { email, password } = req.body;
            const result = await authService.login(email, password);
            res.json(result);
        }
        catch (error) {
            res.status(401).json({ message: (error === null || error === void 0 ? void 0 : error.message) || 'Login failed' });
        }
    }
}
exports.AuthController = AuthController;
