"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.authMiddleware = void 0;
const AuthService_1 = require("@domain/services/AuthService");
const MongoUserRepository_1 = require("@infrastructure/database/repositories/MongoUserRepository");
const authService = new AuthService_1.AuthService(new MongoUserRepository_1.MongoUserRepository());
const authMiddleware = async (req, res, next) => {
    try {
        const authHeader = req.headers.authorization;
        if (!authHeader) {
            return res.status(401).json({ message: 'No token provided' });
        }
        const token = authHeader.split(' ')[1];
        const user = await authService.verifyToken(token);
        // Add user to request object
        req.user = user;
        next();
    }
    catch (error) {
        return res.status(401).json({ message: 'Invalid token' });
    }
};
exports.authMiddleware = authMiddleware;
