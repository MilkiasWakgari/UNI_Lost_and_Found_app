import { Request, Response, NextFunction } from 'express';
import jwt from 'jsonwebtoken';
import mongoose from 'mongoose';

const JWT_SECRET = process.env.JWT_SECRET || 'your_jwt_secret_key_here';

export const authenticateToken = (req: Request, res: Response, next: NextFunction) => {
    const authHeader = req.headers['authorization'];
    const token = authHeader && authHeader.split(' ')[1];
    if (!token || typeof token !== 'string') {
        return res.status(401).json({ message: 'Authentication required' });
    }
    try {
        const decoded = jwt.verify(token, JWT_SECRET) as {
            _id: string;
            email: string;
            role: string;
        };
        req.user = {
            _id: new mongoose.Types.ObjectId(decoded._id),
            email: decoded.email,
            role: decoded.role
        };
        next();
    } catch (error) {
        res.status(401).json({ message: 'Invalid token' });
    }
};

export const requireAdmin = (req: Request, res: Response, next: NextFunction) => {
    if (req.user && req.user.role === 'admin') {
        return next();
    }
    return res.status(403).json({ message: 'Admin access required' });
}; 