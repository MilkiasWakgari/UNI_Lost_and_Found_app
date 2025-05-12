import { Request, Response, NextFunction } from 'express';
import jwt from 'jsonwebtoken';
import mongoose from 'mongoose';

declare global {
    namespace Express {
        interface Request {
            user?: {
                _id: mongoose.Types.ObjectId;
                email: string;
                role: string;
            };
        }
    }
}

export const authMiddleware = (req: Request, res: Response, next: NextFunction) => {
    const authHeader = req.headers['authorization'];
    console.log('Auth header:', authHeader);
    
    const token = authHeader && authHeader.split(' ')[1];
    console.log('Extracted token:', token);
    
    if (!token || typeof token !== 'string') {
        return res.status(401).json({ message: 'Authentication required' });
    }
    try {
        const decoded = jwt.verify(token, process.env.JWT_SECRET || 'your-secret-key') as {
            _id: string;
            email: string;
            role: string;
        };
        console.log('Decoded token payload:', JSON.stringify(decoded, null, 2));
        
        // Ensure we have a valid _id
        if (!decoded._id) {
            console.error('No _id in token');
            return res.status(401).json({ message: 'Invalid token format' });
        }
        
        // Convert the _id to ObjectId
        let userId;
        try {
            userId = new mongoose.Types.ObjectId(decoded._id);
            console.log('Converted userId:', userId);
        } catch (error) {
            console.error('Invalid _id format:', error);
            return res.status(401).json({ message: 'Invalid user ID format' });
        }
        
        req.user = {
            _id: userId,
            email: decoded.email,
            role: decoded.role
        };
        console.log('Set user in request:', req.user);
        
        next();
    } catch (error) {
        console.error('Token verification error:', error);
        res.status(401).json({ message: 'Invalid token' });
    }
}; 