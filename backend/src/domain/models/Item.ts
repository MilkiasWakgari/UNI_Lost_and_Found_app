import mongoose, { Document, Schema } from 'mongoose';

export interface IItem extends Document {
    title: string;
    description: string;
    location: string;
    dateFound: Date;
    timeFound: string;
    category: string;
    status: 'found' | 'claimed' | 'returned';
    imageUrl?: string;
    foundBy: mongoose.Types.ObjectId;
    claimedBy?: mongoose.Types.ObjectId;
    createdAt: Date;
    updatedAt: Date;
}

const itemSchema = new Schema<IItem>({
    title: {
        type: String,
        required: true,
        trim: true
    },
    description: {
        type: String,
        required: true,
        trim: true
    },
    location: {
        type: String,
        required: true,
        trim: true
    },
    dateFound: {
        type: Date,
        required: true,
        default: Date.now
    },
    timeFound: {
        type: String,
        required: true
    },
    category: {
        type: String,
        required: true,
        enum: ['Electronics', 'Clothing', 'Books', 'Accessories', 'Other']
    },
    status: {
        type: String,
        required: true,
        enum: ['found', 'claimed', 'returned'],
        default: 'found'
    },
    imageUrl: {
        type: String
    },
    foundBy: {
        type: Schema.Types.ObjectId,
        ref: 'User',
        required: true
    },
    claimedBy: {
        type: Schema.Types.ObjectId,
        ref: 'User'
    }
}, {
    timestamps: true
});

export const Item = mongoose.model<IItem>('Item', itemSchema); 